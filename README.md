# Example of issue where Mill 0.6.1 does not correctly compile Scala.js projects

This repo recreates the set up of my project, in which you can see the issue with as little stuff as I could manage.

## What is the issue?

### TL;DR

This line (20) silently breaks the Scala.js module fastOpt compilation:

```scala
def scalacPluginIvyDeps = Agg(ivy"org.wartremover:::wartremover:2.4.5")
```

### Description

When compiling a Scala.js project using Mill, the classes definied in the project are not included in the resulting `out.js` file. In the browser, the error you get in the console is:

```
ReferenceError: ClearColor is not defined
```

The line that triggers the bad behaviour is:

```scala
def scalacPluginIvyDeps = Agg(ivy"org.wartremover:::wartremover:2.4.5")
```

If you comment it out, the fastOpt output is consistently:

```
Linker: Compute reachability: 151171 us
Linker: Assemble LinkedClasses: 197694 us
Basic Linking: 495893 us
Emitter: Class tree cache stats: reused: 252 -- invalidated: 260
Emitter: Method tree cache stats: resued: 0 -- invalidated: 1002
Emitter (write output): 316585 us
```

...and that works. Put the line wartremover line back in, you get:

```
Linker: Compute reachability: 169524 us
Linker: Assemble LinkedClasses: 215669 us
Basic Linking: 552414 us
Emitter: Class tree cache stats: reused: 249 -- invalidated: 258
Emitter: Method tree cache stats: resued: 0 -- invalidated: 969
Emitter (write output): 364436 us
```

Which does not work.

Have tried wartremover 2.4.3, 2.4.4, and 2.4.5.

In my real project, the resulting JS file is 380kb instead of the expected 4.7Mb.

## Running the example code

There is a simple build script to get you started that replicates my use case:

```bash
bash localbuild.sh
```

The project has a shared library that is published to your local ivy cache as `foobar#shared`.

`foo-sbt` and `foo-mill` are identical more or less, and both produce a fastOpt(JS) output file.

Both can be tested be going into their respective `testharness` folder and running:

```bash
npm install
npm start
```

Then navigating to `http://localhost:1234/`.

## Replicating the problem / solution

Comment / uncomment line 20 of `foo-mill/build.sc` and rebuild with `mill foo.fastOpt`.

***Note*** I've been deleting the `out` directory between builds just to ensure a clean build.
