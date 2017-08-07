# JSoup for JDK 1.4

For a project, I needed a version of JSoup that compiled using the JDK 1.4 version of Java.  Look in in this repository's releases for a copy of the JDK 1.4-compliant JAR, and later in this README for an example of building a program that uses that JAR.

## Original README

jsoup: Java HTML parser that makes sense of real-world HTML soup.

Status: beta release.

See http://jsoup.org/ for downloads and documentation.

## Build Instructions

Import this as a Maven project in IntelliJ. 
Add a JAR artifact for this project using [these
instructions](https://stackoverflow.com/questions/1082580/how-to-build-jars-from-intellij-properly).
Then build the JAR artifact.

## Test that it works with Java 1.4

Call these instructions from the main repository directory:

```bash
# Compile the example program for target 1.4
# Assumes you have already built the `jsoup.jar` JAR file and that it's in the
# `out/artifacts/jsoup_jar/jsoup.jar` directory.
CLASSPATH=$CLASSPATH:out/artifacts/jsoup_jar/jsoup.jar javac -source 1.4 -target 1.4 ListLinks.java

# Run the JSoup program
CLASSPATH=$CLASSPATH:out/artifacts/jsoup_jar/jsoup.jar java ListLinks https://people.eecs.berkeley.edu/~andrewhead/
```
