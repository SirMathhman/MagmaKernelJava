#include <stdio.h>
#include <stdlib.h>

struct Node {
    int render;
};

char* (**_Node_render)(void*);

char* Node_render(void* instance, struct Node node){
    return _Node_render[node.render](instance);
}

struct Node Node(int render){
    struct Node _Node = {render};
    return _Node;
}

struct VarNode {
    struct Node _node;
    char* value;
};

struct Node VarNode_Node(struct VarNode instance){
    return instance._node;
}

char* VarNode_render(void* instance){
    struct VarNode _VarNode = *(struct VarNode*)(instance);
    return _VarNode.value;
}

struct VarNode VarNode(struct Node _Node, const char* value) {
    struct VarNode _VarNode = {_Node, value};
    return _VarNode;
}

int main() {
    _Node_render = malloc(1* sizeof(void*));
    _Node_render[0] = VarNode_render;

    struct VarNode node = VarNode(Node(0),"This is inheritance?");
    char* result = Node_render(&node, VarNode_Node(node));
    printf("%s", result);

    free(_Node_render);
    return 0;
}
