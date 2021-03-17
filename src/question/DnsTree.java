
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Contains the methods and properties of a dns tree which keeps the nodes in
 * the right order.
 * 
 * @author Salim Kemal Tirit
 *
 */
public class DnsTree {
	private DnsNode root;

	private Map<String, Set<String>> map = new TreeMap<String, Set<String>>();

	/**
	 * Creates a dns tree by creating a root node.
	 */
	public DnsTree() {
		root = new DnsNode();
	}

	/**
	 * Removes the record with the given domain name from the dns tree. Deletes all
	 * of the ip addresses of it using {@link question.DnsNode#flushIpAddresses()}
	 * and also deletes it from its parents child list if the node is a leaf node
	 * (has no child).
	 * 
	 * @param domainName is the domain name of the node to be deleted.
	 * @return false if the given ip address does not exist in the list of the node,
	 *         true if it is removed successfully.
	 */
	public boolean removeRecord(String domainName) {
		DnsNode node = returnNode(domainName);
		if (node == null) {
			return false;
		} else {
			node.flushIpAddresses();
			if (domainName.contains(".")) {// TODO look at this method
				if (node.getChildNodeList().isEmpty()) {
					returnNode(domainName.substring(domainName.indexOf(".") + 1)).getChildNodeList()
							.remove(domainName.substring(0, domainName.indexOf(".")));
				}
			} else {
				if (node.getChildNodeList().isEmpty()) {
					root.getChildNodeList().remove(domainName);
				}
			}
			if (map.containsKey(domainName))
				map.remove(domainName);
			return true;
		}
	}

	/**
	 * Deletes the given ip address of the node whose domain name is also given.
	 * Works exactly same as {@link question.DnsTree#removeRecord(String)} if the
	 * node is not valid after deleting the corresponding ip address.
	 * 
	 * @param domainName is the domain name of the node whose ip will be deleted.
	 * @param ipAddress  is the ip address to be deleted.
	 * @return false if the given ip address does not exist in the list of the node,
	 *         true if it is removed successfully.
	 */
	public boolean removeRecord(String domainName, String ipAddress) {
		DnsNode node = returnNode(domainName);
		if (node == null) {
			return false;
		} else {
			boolean removed = node.removeIpAddress(ipAddress);

			if (!node.isValid()) {
				return removeRecord(domainName);
			}

			return removed;
		}
	}

	/**
	 * Finds the ip address of the node with the given domain name.
	 * 
	 * @param domainName is the domain name of the node whose ip will be returned.
	 * @return null if there is no node with the given domain name otherwise returns
	 *         the ip using {@link question.DnsNode#requestIpAddress()}
	 */
	public String queryDomain(String domainName) {
		DnsNode node = returnNode(domainName);
		if (node == null) {
			return null;
		} else {
			return node.requestIpAddress();
		}
	}

	/**
	 * Fills and returns a map of dns nodes and a set of their ip addresses using by
	 * traversing the tree.
	 *
	 * @return a map of valid dns nodes and a set of their ip addresses
	 * 
	 */
	public Map<String, Set<String>> getAllRecords() {
		ArrayList<String> arr = new ArrayList<>();
		traverseTree(root, arr);
		return map;
	}

	/**
	 * Inserts a dns node into the dns tree. Finds the proper position for the node
	 * and creates it there.
	 * 
	 * @param domainName is the domain name of the node to be created.
	 * @param ipAddress  is the ip address of the node to be created.
	 */
	public void insertRecord(String domainName, String ipAddress) {
		StringTokenizer tokenizer = new StringTokenizer(domainName, ".", false);
		ArrayList<String> arr = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			arr.add(0, tokenizer.nextToken());
		}
		DnsNode current = root;
		for (int i = 0; i < arr.size(); i++) {
			if (current.getChildNodeList().containsKey(arr.get(i))) {
				current = current.getChildNodeList().get(arr.get(i));
				continue;
			} else {
				DnsNode node = new DnsNode();
				current.getChildNodeList().put(arr.get(i), node);
				current = node;
			}
		}
		current.addIp(ipAddress);
	}

	private DnsNode returnNode(String nodeName) {
		StringTokenizer tokenizer = new StringTokenizer(nodeName, ".", false);
		ArrayList<String> arr = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			arr.add(0, tokenizer.nextToken());
		}
		DnsNode current = root;
		for (int i = 0; i < arr.size(); i++) {
			if (current.getChildNodeList().containsKey(arr.get(i))) {
				current = current.getChildNodeList().get(arr.get(i));
				continue;
			} else {
				return null;
			}
		}
		return current;
	}

	private void traverseTree(DnsNode node, ArrayList<String> current) {
		if (node.getChildNodeList().isEmpty()) {
			return;
		} else {
			Iterator<Entry<String, DnsNode>> i = node.getChildNodeList().entrySet().iterator();
			while (i.hasNext()) {
				Entry<String, DnsNode> e = i.next();
				current.add(e.getKey());
				if (e.getValue().isValid()) {
					String temp = "";
					for (int j = 0; j < current.size(); j++) {
						temp = "." + current.get(j) + temp;
					}
					temp = temp.substring(1);
					map.put(temp, e.getValue().getIpAddresses());
				}
				traverseTree(e.getValue(), current);
				if (current.size() > 0) {
					current.remove(current.size() - 1);
				}
			}
		}
	}

	//TODO delete this
	public DnsNode getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
