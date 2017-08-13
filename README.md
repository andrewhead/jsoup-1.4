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
Then build the JAR artifact.  You should be using JDK 1.6 to compile the
JAR.  I tried building it with JDK 1.8, and it failed.

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

## A more advanced test case

This example pulls a page from Craigslist matching a query,
parses its content for links, titles, and prices, formats a
message, and sends it to you in email.  I used something
like this to help me with apartment searching a few years
back; this is a version of that script written in Java.

To send email, I recommend you create a throwaway Gmail
account that you want all email to be sent from.

Once you have created that Gmail account, ceate a file
called `/etc/smtp.conf`.  You will probably need sudo
permissions to do this.  And it is probably only doable on
OSX and Linux.

In that file put two lines substituting in the username and
password of your new Gmail account.  Yes, with the current
example code, the password needs to be in plaintext.  This
is why I recommend it's a throwaway email account that you
use for no serious purposes.

```
<my-gmail-username@gmail.com>
<my-password>
```

Then you can compile and run the program like this.  First,
compile the program:

```bash
CLASSPATH=:out/artifacts/jsoup_jar/jsoup.jar:deps/javax.mail-1.4.7.jar \
  javac \
    -source 1.4 \
    -target 1.4 \
    CraigslistMonitor.java
```

Then run the program like this:

```bash
CLASSPATH=:out/artifacts/jsoup_jar/jsoup.jar:deps/javax.mail-1.4.7.jar \
  java CraigslistMonitor \
    "electric bike" \
    head.andrewm@gmail.com
```

The first argument to the program is a query to be run on
Craigslist.  The second argument is an email address to
which to send a report of what was found on Craigslist.
During my tests, the email is delivered within seconds of
the program completing.

The Craigslist search is performed only in the East Bay.
Take a look at the base path in the Craigslist URL if you
want to do the search in another region.
