#include <stdio.h>

struct DoubleLinkedList
{
    struct Node *start;
};

struct DoubleLinkedList *createDoubleLinkedList(int *data[])
{

    struct Node *startNode = createNode(data[0]);

    struct Node *lastNode = startNode;

    for (int index = 1; index < (sizeof(data) / sizeof(data[0])); index++)
    {
        struct Node *currentNode = createNode(data[index]);
        currentNode->data = data[index];
        currentNode->prev = lastNode;

        lastNode->next = currentNode;
        lastNode = currentNode;
    };

    return startNode
}

struct Node
{
    int data;
    struct Node *prev;
    struct Node *next;
};

struct Node *
createNode(int new_data)
{
    struct Node *new_node = (struct Node *)
        malloc(sizeof(struct Node));
    new_node->data = new_data;
    new_node->next = NULL;
    new_node->prev = NULL;
    return new_node;
}

void forwardTraversal(struct Node *head)
{
    // Start traversal from the head of the list
    struct Node *curr = head;

    // Continue until the current node is not
    // null (end of list)
    while (curr != NULL)
    {

        // Output data of the current node
        printf("%d ", curr->data);

        // Move to the next node
        curr = curr->next;
    }

    // Print newline after traversal
    printf("\n");
}