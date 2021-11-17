# Monitoring Metrics with JMC <small>(JDK Mission Control)</small>

<video muted autoplay loop style="width: 100%" poster="_media/frame.jpg">
    <source type="video/mp4;codecs=vp9"
        src="https://user-images.githubusercontent.com/83819/142078723-d1d52394-5f13-4b2d-a7b2-120bbc01013f.mp4">
</video>

# Introduction

## Who is this for?

This document is for contributors to Terasology—
mainly developers and testers—
who want a way to peek under the hood while the game is running.

Some of these techniques can be used without a full Terasology development environment,
but you should be comfortable running Java applications from the command line.

## How is this different than Java Flight Recordings?

If you've read [Analyzing JFR Recordings], you might be wondering how this is different.

[Analyzing JFR Recordings]: https://github.com/Terasology/TutorialProfiling/wiki/Analyzing-JFR-Recordings

Flight recordings are detailed logs of events over a short period of time.
You can analyze a flight recording to learn about what happened during that time in the past.

Here we'll be exploring a different feature of JMC, its JMX Console.<sup>1</sup>
The JMX Console lets you see the _current state_ of a process.
Instead of reading a log recorded by the application, it polls it for the current value of metrics you're interested in.

<div style="display: flex">
<div>

**JFR**

- 🖨 writes a log file
- 📥 can be attached to a bug report for later review

</div>
<div>

**JMX**

- 🩺 connects to a live process
- 📡 can connect to a remote server

</div>
</div>

<!-- The line gets increasingly blurry as Java 14 added streaming features to JFC, and JFR-over-JMX is expected. -->

<!-- Footnote: -->
1. When you see “MBean Server” in Mission Control, it's referring to the same thing.
Its JMX Console is the interface it provides to an application's MBean server. 


# Prerequisite: Install JMC

Download the latest stable release of [JDK Mission Control from AdoptOpenJDK](https://adoptopenjdk.net/jmc.html).

Unpack the archive and run the program inside:`jmc`

<!-- As of November 2021, Adoptium does not yet provide JMC. -->


# Local Use

## Connecting to Terasology

## Terasology's Metrics

- adding custom charts

## Adding a Connection

### Creating a password file

- create `jmxremote.password`

### Setting the ports

- configuring the process
  - `gradle game --jmx-port`
  - with Terasology.bat or .sh: set `TERASOLOGY_OPTS`
  - with Launcher: ???

### New Connection in JMC

- creating the connection
- your charts should now be persistent

# Remote Use

- same process as “adding a connection”
- use a SSH tunnel?


# Adding New Metrics to Terasology

🚧 We haven't settled on the API for this yet.
Add a meter to Micrometer's global registry, and it'll be available via JMX.
