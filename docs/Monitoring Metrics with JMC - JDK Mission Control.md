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

#### Footnotes
1. When you see “MBean Server” in Mission Control, it's referring to the same thing.
Its JMX Console is the interface it provides to an application's MBean server. 


# Prerequisite: Install JMC

Download the latest stable release of [JDK Mission Control from AdoptOpenJDK](https://adoptopenjdk.net/jmc.html).

Unpack the archive and run the program inside:`jmc`

<!-- As of November 2021, Adoptium does not yet provide JMC. -->


# Local Use

## Connecting to Terasology

The JVM Browser in JMC shows all Java processes running on your computer:

<img src="_media/JMC/JMC JVM Browser.webp" width="599" height="271" alt="Each is listed with its main class and process identifier. Selecting a process reveals several options." >

Choose the MBean Server for the Terasology process.
<img src="_media/JMC/JMC JMX Console - Default.halfsize.webp" width="344" height="300" style="float: right;" alt="The dashboard shows current memory and CPU usage on speedometer-style gauges, and charts recent values on line graphs.">

This should take you to an Overview with charts showing the process's current resource usage.

<br style="clear: both">

## Terasology's Metrics

By default, the Overview shows you some measurements common to all Java processes: Memory and CPU usage.
What if we're interested in something else?

We can create custom charts to include other metrics, including those unique to the Terasology engine.
Please experiment here and make the charts your own, but a word of warning:
_do not get too attached to your charts right now._
There is a good chance that JMC will not save them.<sup>2</sup>

We'll talk about how to work around that later, but first let's find out what we can look at.
Make sure you have a Terasology game running for this part;
some metrics are only collected while a world is loaded, and won't show up if you're still at the main menu.

Select the <b>MBean Browser</b> tab:

<img src="_media/JMC/JMC MBean Browser - Filtered.webp" width="381" height="227" alt="The MBean Browser shows a tree with available MBeans, and the attributes of the selected entry.">

Each MBean represents some value we can monitor.
The **Filter** control lets us search them by name:
for example, we can filter by `*fps` to find the entry for the engine's framerate.<sup>3</sup>  

Right-click on its **Number** attribute, choose **Visualize**, and JMC will prompt you about adding it to a chart.

#### Footnotes
2. At least as of JMC 8.1.0, it seems to treat every process as unique. 
When the current process ends and you connect to a different Terasology process later,
it reverts to the default configuration.
3. If your version has something named differently, it's okay.
We haven't figured out how we're categorizing all these things yet.
Regardless of the name, as long as it has a Number attached to it, you can chart it.

# Adding a Connection

## Creating a password file

- create `jmxremote.password`

## Setting the ports

- configuring the process
  - `gradle game --jmx-port`
  - with Terasology.bat or .sh: set `TERASOLOGY_OPTS`
  - with Launcher: ???

## New Connection in JMC

- creating the connection
- your charts should now be persistent

# Remote Use

- set up jmx port, create Connection
- use a SSH tunnel?


# Adding New Metrics to Terasology

🚧 We haven't settled on the API for this yet.
Add a meter to Micrometer's global registry, and it'll be available via JMX.
