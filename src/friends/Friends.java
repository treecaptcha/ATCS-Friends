package friends;

import java.util.ArrayList;
import structures.Queue;
import structures.Stack;

/**
 * A Friends class
 * 
 * @author Your name
 * @date Due Date
 * @period Class Period
 */
public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. null if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		Person person1 = g.members[g.map.get(p1)];
		int person2 = g.map.get(p2);

		ArrayList<Integer> visited= new ArrayList<>();
		visited.add(g.map.get(p1));

		Queue<Stack<Person>> paths = new Queue<>();
		Stack<Person> initStack = new Stack<>();
		initStack.push(person1);
		paths.enqueue(initStack);

		while(!paths.isEmpty()){
			Stack<Person> cur = paths.dequeue();
			Friend friend = cur.peek().first;
			while (friend != null){
				if(visited.contains(friend.fnum)) {friend = friend.next; continue;}
				visited.add(friend.fnum);

				Stack<Person> copy = stackCopy(cur, g.members[friend.fnum]);
				paths.enqueue(copy);

				if(friend.fnum == person2) return asArrayList(copy);
				friend = friend.next;

			}

		}
		// No such path
		return null;

	}

	private static <T> Stack<T> stackCopy(Stack<T> stack, T bonus){
		Stack<T> antiStack = new Stack<>();
		for (int i = stack.size(); i > 0; i--) {
			antiStack.push(stack.pop());
		}

		Stack<T> copyStack= new Stack<>();
		for (int i = antiStack.size(); i > 0; i--) {
			T pop = antiStack.pop();
			stack.push(pop);
			copyStack.push(pop);
		}
		copyStack.push(bonus);
		return copyStack;
	}

	private static ArrayList<String> asArrayList(Stack<Person> stack){
		ArrayList<String> list = new ArrayList<>();
		while (!stack.isEmpty()){
			list.add(0, stack.pop().name);
		}
		return list;
	}

	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an ArrayList of ArrayLists - each constituent ArrayList contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return ArrayList of cliques, where each clique is an ArrayList of names. 
	 * 				null if there is no student in the given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		ArrayList<ArrayList<String>> cliques= new ArrayList<>();
		for( Person person:g.members){
			Friend f = person.first;
			if (!school.equals(person.school)) continue;
			while(f != null) {
				Person friend = g.members[f.fnum];
				if (!school.equals(friend.school)) {f = f.next; continue;}

				int prev = -1;
				for (int i = 0; i < cliques.size(); i++) {
					if (cliques.get(i).contains(friend.name)){
						cliques.get(i).add(person.name);
						if (prev != -1){
							cliques.get(prev).addAll(cliques.remove(i));
							i--;
						}
						prev = i;
					}
				}
				f = f.next;
			}
		}
		return cliques;
	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return an ArrayList of names of all connectors. null if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		/** COMPLETE THIS METHOD **/
		return null;
	}
}

