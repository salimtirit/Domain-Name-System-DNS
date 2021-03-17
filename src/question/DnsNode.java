
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Salim Kemal Tirit
 *
 */
public class DnsNode {
	private Map<String, DnsNode> childNodeList;

	private boolean validDomain;

	private Set<String> ipAddresses;

	/**
	 * Creates the fields of the class.
	 */
	public DnsNode() {
		childNodeList = new HashMap<String, DnsNode>();

		ipAddresses = new LinkedHashSet<String>();

		validDomain = false;
	}

	/**
	 * 
	 * @return the set of ip addresses of this node.
	 */
	public Set<String> getIpAddresses() {
		return ipAddresses;
	}

	/**
	 * Adds an ip address to the set of ip addresses.
	 * 
	 * @param ip is the ip adress to be added
	 */
	public void addIp(String ip) {
		ipAddresses.add(ip);
		validDomain = true;
	}

	/**
	 * Removes the given ip address from the list of ip addresses if the set
	 * contains the ip. If there is no other ip left the node is not valid anymore.
	 * 
	 * @param ipAddress is the ip address to be removed.
	 * @return true if it is removed, false if it does not exist.
	 */
	public boolean removeIpAddress(String ipAddress) {
		if (this.ipAddresses.contains(ipAddress)) {
			this.ipAddresses.remove(ipAddress);
			if (this.ipAddresses.isEmpty()) {
				validDomain = false;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Deletes all the ip addresses and the node is not valid anymore.
	 */
	public void flushIpAddresses() {// TODO check where you use this method
		this.ipAddresses.clear();
		this.validDomain = false;
	}

	/**
	 * If the node has any ip address it is valid otherwise it is not.
	 * 
	 * @return the validity of the node.
	 */
	public boolean isValid() {
		return this.validDomain;
	}

	/**
	 * Returns the next ip of the node. If there are more than one ip address of the
	 * node this method tries to return them one by one in a loop.
	 * 
	 * @return an ip address of the node following an algorithm called "Round
	 *         Robin".
	 */
	public String requestIpAddress() {
		if (this.validDomain && !ipAddresses.isEmpty()) {
			Iterator<String> i = ipAddresses.iterator();
			String next = i.next();
			i.remove();
			ipAddresses.add(next);
			return next;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return the child node list of this node.
	 */
	public Map<String, DnsNode> getChildNodeList() {
		return childNodeList;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
