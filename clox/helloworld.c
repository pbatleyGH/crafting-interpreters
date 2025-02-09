#include <stdio.h>
#include "DoubleLinkedList.c"

int main()
{

    printf("Hello, World!\n");

    int data[] = {10, 20, 30};
    struct DoubleLinkedList *dll = createDoubleLinkedList(*data);

    return 0;
}