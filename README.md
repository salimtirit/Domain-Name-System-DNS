# Basic DNS Implementation
In this project, I implemented a basic Domain Name System (DNS) mechanism using Java. DNS is a distributed and hierarchical naming system used to translate domain names into corresponding IP addresses for accessing requested services on the internet.

## Introduction
DNS is a critical component of the internet infrastructure, and it is essential for the successful operation of web services. In this project, I implemented a simple version of DNS as a tree structure, where the domains are organized in this tree accordingly.
![image](https://user-images.githubusercontent.com/64011660/232545306-b3e4fb91-deab-428c-b438-4f4c3a89c5d1.png)

## Implementation Details
The project consists of a single package named "question," and all the classes should be implemented in this package. I was provided with the empty class files and needed to complete the implementation of these classes.
![image](https://user-images.githubusercontent.com/64011660/232545387-9c9013c4-47f0-4da1-b356-71d0440fc19d.png)

### DnsNode.java
This class represents each of the nodes in the DNS tree structure. The following fields should be defined for this class:

* `Map<String, DnsNode> childNodeList`: This field is used for maintaining the tree structure. The keys are represented as strings, and the values are DNS nodes. Each key is a subdomain name, and the value it is mapped represents a child node in the tree structure.
* `boolean validDomain`: It shows whether the current node is a valid domain name or just a subdomain that cannot have any IP address.
* `Set<String> ipAddresses`: This set is for storing the IP addresses as a list of a domain name.

The constructor of this class creates a DNS node with an empty _childNodeList_ and _ipAddresses_. Besides, the _validDomain_ is initialized as “false”. After adding the first IP address to the list of a node, its _validDomain_ should be set to true since it represents a valid domain name with a corresponding IP address.

### Client.java
The Client class represents the client-side and consists of a simple implementation of the cache mechanism used in DNS. Within this class, a nested private class _CachedContent_ is implemented. The _CachedContent_ class has the following fields:

* `String domainName`
* `String ipAddress`
* `int hitNo`

After obtaining an IP address for the requested web domain name, it is stored in the cache by using the necessary information. The cache can store only 10 different domain-IP information on a local device. In order to store the 11th one, an old record should be removed. The cache replacement algorithm removes the cache that is least used. This information is provided through `int hitNo`, which is incremented by one when the local device uses this information to access a web server without requesting the DNS.

### DnsTree.java
This class represents the main DNS structure. The only field it should have is `DnsNode root` 
which is the root of the tree. Correspondingly, the constructor of this class only creates and 
initializes the root node.
This class acts as a DNS server. The server should be able to add a new domain name and its corresponding IP address to the DNS tree and return the IP address of a given domain name.

## Conclusion
In this project, I implemented a simple version of the Domain Name System (DNS) mechanism as a tree structure using Java. This implementation allowed me to understand the basic functionalities and structure of DNS and how it can be used to translate domain names into IP addresses.
