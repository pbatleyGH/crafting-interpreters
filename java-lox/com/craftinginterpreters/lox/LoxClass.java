package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;

// thinking:
// a class also has an instance of itself,
//   what closure? presumably just the current env?
// loop over any static (`class X`) methods and set them on the env

// for parsing, the `classDeclaration` will need to know whether its inside a class already
// maybe we just extend `method` for this?

class LoxClass extends LoxInstance implements LoxCallable {
    final String name;
    final LoxClass superclass;
    private final Map<String, LoxFunction> methods;

    LoxClass(String name, LoxClass superclass, Map<String, LoxFunction> methods, LoxClass metaclass) {
        // metaclass is how we create a static instance (LoxInstance) of the class to
        // provide access to static methods
        super(metaclass);

        this.superclass = superclass;
        this.name = name;
        this.methods = methods;

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter,
            List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction initializer = findMethod("init");

        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public int arity() {
        LoxFunction initializer = findMethod("init");
        if (initializer == null)
            return 0;
        return initializer.arity();
    }

    LoxFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        if (superclass != null) {
            return superclass.findMethod(name);
        }

        return null;
    }
}
