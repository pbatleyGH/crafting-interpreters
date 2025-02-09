#include <stdio.h>
#include <stdlib.h>

// Define the Node structure
struct Node
{
    int data;          // Data stored in the node
    struct Node *next; // Pointer to the next node
    struct Node *prev; // Pointer to the previous node
};

// Function to create a new node
struct Node *createNode(int data)
{
    struct Node *newNode =
        (struct Node *)
            malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    newNode->prev = NULL;
    return newNode;
}