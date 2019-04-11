#include <iostream>
#include <string>

using namespace std;

template <typename T>
class Leaf {
private:
	T data;
	Leaf *left, *right;

public:
	void insert(T a, Leaf **w) {
		if ((*w) == NULL) {	//puste drzewo
			(*w) = new Leaf;
			(*w)->data = a;
			(*w)->left = NULL;
			(*w)->right = NULL;
		}
		else {
			if (a > (*w)->data) {
				insert(a, &(*w)->right);
			}
			else {
				insert(a, &(*w)->left);
			}
		}
	}

	void draw(Leaf **w) {
		if ((*w) != NULL) {
			cout << (*w)->data << endl;
		}
		if ((*w)->left != NULL) {
			draw(&(*w)->left);
		}
		if ((*w)->right != NULL) {
			draw(&(*w)->right);
		}
	}

	bool search(T a, Leaf **w) {
		if ((*w) == NULL)
			return false;
		if (a > (*w)->data) {
			return search(a, &(*w)->right);
		}
		if (a < (*w)->data) {
			return search(a, &(*w)->left);
		}
		if (a == (*w)->data) {
			return true;
		}
	}

	Leaf* deleteW(Leaf *w, T data) {
		if (w == NULL)
			return w;
		else if (data < w->data)
			w->left = deleteW(w->left, data);
		else if (data > w->data)
			w->right = deleteW(w->right, data);
		else {	//znalezlismy
			if (w->left == NULL && w->right == NULL) {
				delete w;
				w = NULL;
			}
			else if (w->left == NULL) {
				Leaf *tmp = findMin(w->right);
				w->data = tmp->left->data;
				tmp->left = NULL;
				delete tmp->left;
			}
			else if (w->right == NULL) {
				Leaf *tmp = findMax(w->left);
				w->data = tmp->right->data;
				tmp->right = NULL;
				delete tmp->right;
			}
			else {
				if (w->right->left)
				{
					Leaf *tmp = findMin(w->right)->left;
					w->data = tmp->data;
					if (tmp->right != NULL) {
						findMin(w->right)->left = tmp->right;
						delete tmp;
					}
					else
					{
						findMin(w->right)->left = NULL;
						delete tmp;
					}
				}
				else if (w->left->right)
				{
					Leaf *tmp = findMax(w->left)->right;
					w->data = tmp->data;
					if (tmp->left != NULL) {
						findMax(w->left)->right = tmp->left;
						delete tmp;
					}
					else
					{
						findMax(w->left)->right = NULL;
						delete tmp;
					}
				}
			}
		}
		return w;
	}

	Leaf* findMin(Leaf* w) {
		while (w->left->left != NULL)
			w = w->left;
		return w;
	}

	Leaf* findMax(Leaf* w) {
		while (w->right->right != NULL)
			w = w->right;
		return w;
	}
};

int main() {
	Leaf <string> * Stree = NULL;
	Leaf <int> * Itree = NULL;
	Leaf <double> * Dtree = NULL;

	string Sdane;
	int Idane;
	double Ddane;

	Dtree->insert(8, &Dtree);
	Dtree->insert(10, &Dtree);
	Dtree->insert(14, &Dtree);
	Dtree->insert(13, &Dtree);
	Dtree->insert(3, &Dtree);
	Dtree->insert(1, &Dtree);
	Dtree->insert(6, &Dtree);
	Dtree->insert(7, &Dtree);
	Dtree->insert(5, &Dtree);
	Dtree->insert(5.5, &Dtree);

	Dtree->draw(&Dtree);
	cout << " " << endl;

	Dtree->deleteW(Dtree, 3);
	Dtree->draw(&Dtree);
	cout << " " << endl;

	Dtree->deleteW(Dtree, 8);
	Dtree->draw(&Dtree);
	cout << " " << endl;


	/*double elem = 3;
	if ((Itree->search(elem, &Itree)) == true)
	cout << "Jest taki w drzewie" << endl;
	else
	cout << "Nie ma takiego" << endl;

	elem = 7;
	if ((Itree->search(elem, &Itree)) == true)
		cout << "Jest taki w drzewie" << endl;
	else
		cout << "Nie ma takiego" << endl;
		*/

	system("pause");
	return 0;
}
