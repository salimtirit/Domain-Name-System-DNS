
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

/**
 * Package of user of the program. Tries to reach a node. For this purpose sends
 * a request to dns tree. Also contains a cache to store the returned nodes from
 * dns tree.
 * 
 * @author Salim Kemal Tirit
 *
 */
public class Client {

	private class CachedContent {
		private String domainName;
		private String ipAdress;
		private int hitNo;

		private CachedContent(String domainName, String ipAdress) {
			this.domainName = domainName;
			this.ipAdress = ipAdress;
			this.hitNo = 0;
		}
		
		@Override
		public String toString() {
			return this.domainName + "-" + this.hitNo;

		}
	}

	private DnsTree root;
	private String ipAddress;
	private CachedContent[] cacheList;

	/**
	 * Creates a client by taking two parameters. Ip address must be unique but
	 * since it is not used in this project it does not matter. Creates a cache list
	 * which can store ten elements by default.
	 * 
	 * @param ipAddress defines the identity of the client.
	 * @param root      is the root of the dns tree.
	 */
	public Client(String ipAddress, DnsTree root) {
		this.ipAddress = ipAddress;
		this.root = root;
		cacheList = new CachedContent[10];
	}

	public String sendRequest(String domainName) {
		boolean contains = false;
		int index = 0;
		for (int i = 0; i < cacheList.length; i++) {
			if (cacheList[i] != null && cacheList[i].domainName.equals(domainName)) {
				contains = true;
				index = i;
				break;
			}
		}
		if (contains) {
			cacheList[index].hitNo++;
			return cacheList[index].ipAdress;
		} else {
			String ip = root.queryDomain(domainName);
			addToCache(domainName, ip);
			return ip;
		}
	}

	private void addToCache(String domainName, String ipAddress) {
		boolean added = false;
		for (int i = 0; i < cacheList.length; i++) {
			if (cacheList[i] == null) {
				cacheList[i] = new CachedContent(domainName, ipAddress);
				added = true;
				break;
			}
		}
		if (!added) {
			int index = 0;
			int minNumberOfHits = Integer.MAX_VALUE;
			for (int i = 0; i < cacheList.length; i++) {
				if (cacheList[i].hitNo < minNumberOfHits) {
					minNumberOfHits = cacheList[i].hitNo;
					index = i;
				}
			}
			cacheList[index] = new CachedContent(domainName, ipAddress);
		}

	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
