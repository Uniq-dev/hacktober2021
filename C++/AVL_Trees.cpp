#include <iostream>

using namespace std;

struct node {
    struct node* left;
    struct node* right;
    int data;
    int height;
};

struct node* root = NULL;

void preordertraversal(struct node* temp)
{
    if (temp != NULL) {

        cout << temp->data << " ";
        preordertraversal(temp->left);
        preordertraversal(temp->right);
    }
}

int getheight(struct node* temp)
{
    if (temp == NULL)
        return 0;
    else {
        return temp->height;
    }
}

int getmax(int a, int b)
{
    if (a > b)
        return a;
    else
        return b;
}

int getbalance(struct node* temp)
{
    if (temp == NULL)
        return 0;
    return getheight(temp->left) - getheight(temp->right);
}

struct node* lrotate(struct node* z)
{
    struct node* y = z->right;
    struct node* x = y->left;

    y->left = z;
    z->right = x;

    z->height = 1 + max(getheight(z->left), getheight(z->right));
    y->height = 1 + max(getheight(y->left), getheight(y->right));

    return y;
}

struct node* rrotate(struct node* z)
{
    struct node* y = z->left;
    struct node* x = y->right;

    y->right = z;
    z->left = x;

    z->height = 1 + max(getheight(z->left), getheight(z->right));
    y->height = 1 + max(getheight(y->left), getheight(y->right));

    return y;
}

struct node* createnewnode(int d)
{
    struct node* t = (struct node*)malloc(sizeof(struct node));
    t->left = NULL;
    t->right = NULL;
    t->data = d;
    t->height = 1;
    return t;
}

int displaykrnekayanahi = 0, displaykrnekayanahi1 = 0;

struct node* insert(struct node* temp, int d)
{

    if (temp == NULL)
        return createnewnode(d);

    if ((temp->data) < d)
        temp->right = insert(temp->right, d);
    else if ((temp->data) > d)
        temp->left = insert(temp->left, d);
    else
        return temp;

    temp->height = 1 + max(getheight(temp->left), getheight(temp->right));

    if (displaykrnekayanahi == 0) {
        cout << "Before Balancing tree the unbalanced tree looks like \n";
        preordertraversal(root);
        displaykrnekayanahi = 1;
    }
    int balance = getbalance(temp);

    if (balance > 1 && d < temp->left->data) {
        return rrotate(temp);
    }
    else if (balance > 1 && d > temp->left->data) {
        temp->left = lrotate(temp->left);
        return rrotate(temp);
    }
    else if (balance < -1 && d > temp->right->data) {
        return lrotate(temp);
    }
    else if (balance < -1 && d < temp->right->data) {
        temp->right = rrotate(temp->right);
        return lrotate(temp);
    }

    return temp;
}
void create()
{
    int n, r;
    cout << "\nHow many numbers you wanna enter into the AVL tree ";
    cin >> n;
    cout << "\nNow enter the numbers: - ";

    for (int i = 0; i < n; i++) {
        cin >> r;
        root = insert(root, r);
    }

    cout << "\nBinary Search tree has been created succesfully";

    return;
}

struct node* inordersuccessor(struct node* t)
{
    struct node* temp = t;

    while (temp && temp->left != NULL)
        temp = temp->left;

    return temp;
}
struct node* deletenode(struct node* t, int d)
{
    if (t == NULL)
        return t;

    if (d < t->data)
        t->left = deletenode(t->left, d);
    else if (d > t->data)
        t->right = deletenode(t->right, d);

    else {
        if (t->left == NULL) {
            struct node* temp = t->right;
            free(t);
            return temp;
        }
        else if (t->right == NULL) {
            struct node* temp = t->left;
            free(t);
            return temp;
        }
        else {
        }

        struct node* temp = inordersuccessor(t->right);
        t->data = temp->data;
        t->right = deletenode(t->right, temp->data);
    }
    t->height = 1 + max(getheight(t->left), getheight(t->right));

    if (displaykrnekayanahi1 == 0) {
        cout << "Before Balancing tree the unbalanced tree looks like \n";
        preordertraversal(root);
        displaykrnekayanahi1 = 1;
    }
    int balance = getbalance(t);

    if (balance > 1 && getbalance(t->left) >= 0) {
        return rrotate(t);
    }
    else if (balance > 1 && getbalance(t->left) < 0) {
        t->left = lrotate(t->left);
        return rrotate(t);
    }
    else if (balance < -1 && getbalance(t->right) <= 0) {
        return lrotate(t);
    }
    else if (balance < -1 && getbalance(t->right) > 0) {
        t->right = rrotate(t->right);
        return lrotate(t);
    }

    return t;
}

int main()
{
    int check = 1;
    do {
        cout << endl;
        cout << "\n1. create 2.display 3. insert 4.Delete ";
        cout << "\nEnter your choice ";
        cin >> check;
        switch (check) {

        case 1:
            displaykrnekayanahi = -1;
            create();
            break;

        case 2:

            if (root == NULL)
                cout << "Tree is empty";
            preordertraversal(root);
            break;
        case 3:
            displaykrnekayanahi = 0;
            int d;
            cout << "Enter the value you wanna insert ";
            cin >> d;
            root = insert(root, d);
            cout << "\nValue has been inserted succesfully. ";
            cout << "After Balancing  the tree looks like : -\n";
            preordertraversal(root);
            break;

        case 4:
            displaykrnekayanahi1 = 0;
            cout << "Enter the value you wanna delete ";
            cin >> d;
            root = deletenode(root, d);
            cout << "\nValue has been deleted succesfully. ";
            cout << "After Balancing  the tree looks like : -\n";
            preordertraversal(root);
            break;
        }

    } while (check >= 1 && check <= 4);

    return 0;
}