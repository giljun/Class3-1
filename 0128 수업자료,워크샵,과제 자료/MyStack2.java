package test03;

public class MyStack2<T> {
	private Node<T> top;

//////////////////////////////////////////////////////////////
	public MyStack2() {
		top = new Node<>(null); // top 노드는 스택에 push될 때 맨 위 노드의 주소만 저장하면 됨. 데이터 노노함.
		// 현재 top.next 에는 null이 저장되어 있음. 즉 스택에 아무도 없음.
	}

	public void push(T data) {
		Node<T> newNode = new Node<>(data);

		if (isEmpty()) { // 스택이 비어있는 상태에서 첫 노드객체의 push라면 그 객체 주소를 top.next에 저장하면 됨.
			top.next = newNode;
		} else {
			// 지금 추가되는 애 바로 아래 있는 노드주소(나 이전에 제일 위에 있던 놈의 주소)를 내가 기억하고
			newNode.next = top.next;
			top.next = newNode; // top은 이제 나를 바라보게 하기
		}
	}

	public T pop() {
		T popData = top.next.data; // 탑이 주소를 기억하는 노드에 접근해서 데이터 얻어오기

		top.next = top.next.next; // pop되는 노드객체가 가지고있던 지 아래 객체의 주소 얻어서
		return popData;
	}

	public boolean isEmpty() {
		if (top.next == null) { // top 노드의 참조변수 next에 주소가 없으면 가리키는 노드 객체가 없다는 것!
			return true;
		} else {
			return false;
		}
	}

//////////////////////////////////////////////////////////////
	private class Node<E> { // E가 어떤 자료형인지는 스택에서 push 진행할 때 new Node() 하면서 알려줌.
		E data;
		Node<E> next;

		Node(E data) {
			this.data = data;
			next = null;
		}
	}
}
