package com.craftinginterpreters.lox;

class RPNPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return rpnOrder(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return expr.expression.accept(this).toString();
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) {
            return "nil";
        }
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return rpnOrder(expr.operator.lexeme, expr.right);
    }

    // from solutions: we don't need this helper any more, just use this in the
    // Binary visitor:
    // return expr.left.accept(this) + " " +
    // expr.right.accept(this) + " " +
    // expr.operator.lexeme;
    private String rpnOrder(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        for (Expr expr : exprs) {
            builder.append(expr.accept(this));
            builder.append(" ");
        }

        if (name != "group") {
            builder.append(name);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Expr sum = new Expr.Binary(new Expr.Literal(10), new Token(TokenType.PLUS, "+", null, 1), new Expr.Literal(64));

        Expr expression1 = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(new Expr.Literal(45.67)));

        Expr expression2 = new Expr.Binary(
                new Expr.Grouping(
                        new Expr.Binary(new Expr.Literal(1),
                                new Token(TokenType.PLUS, "+", null, 1),
                                new Expr.Literal(2))),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Binary(new Expr.Literal(4),
                                new Token(TokenType.MINUS, "-", null, 1),
                                new Expr.Literal(3))));

        System.out.println(new RPNPrinter().print(sum));
        System.out.println(new RPNPrinter().print(expression1));
        System.out.println(new RPNPrinter().print(expression2));
    }
}
