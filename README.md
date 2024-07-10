Java Web Crawler Project: This repository implements a multi-threaded web crawler in Java, designed to fetch and parse web pages concurrently. It includes classes for managing URL queues, tracking visited URLs, parsing HTML content, and handling data storage, rate limiting, and robots.txt compliance.

Since the web crawler is IO task intensive, it makes sense to have a multi threaded program that fetches data from several URLs at once. The number of CPU cores on my device is 16, and I've decided to implement about 25 threads to fetch different URLs.
