WebCrawler
=======

# Preconditions 
- JDK11
- MVN 3.6

# How to run it
- First it is needed to build solution using `mvn clean package`
- From terminal launch command `mvn exec:java` (default page is http://www.dustyfeet.com)
- For specific site please provide with command `-Dexec.args="http://mysmallwebpage.com/"` with page you are interested in
- If was able to create file, then it should create file called `report.txt`. If creating file would fail then solution gonna be print by `system out`.

# What could be done with more time
- For things that could be done, please have a look into internal issue manager `fira.txt` in repository
