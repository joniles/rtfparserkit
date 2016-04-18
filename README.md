RTF Parser Kit
==============

I have often been frustrated by the lack of comprehensive support for working with RTF in Java, and the need to use RTF parsers which are incomplete and form part of larger projects whose libraries I don't want to import just to use the RTF parser. The RTF Parser Kit project is an attempt to address these points.

The idea is to provide a "kit" of components which can either be used "as-is", for example to extract plain text or HTML from an RTF file, or can be used as a component in a larger application which requires the capability to parse RTF documents.

What's currently included?
--------------------------
* Raw RTF Parser - parses RTF, sends events representing content to a listener. Performs minimal processing - you get the RTF commands and data exactly as they appear in the file.
* Standard RTF Parser - parses RTF, sends events representing content to a listener. Handles character encoding, Unicode and so on, so you don't have to. This is probably the parser you want to use.
* Text Converter - demonstrates very simple text extraction from an RTF file
* RTF Dump - another demonstration, this time writing the RTF file contents as XML

Getting Started
===============

You have a choice of two parsers to work with, the standard parser and the raw parser. The raw parser carries out minimal processing on the RTF, the standard parser handles character encodings, and translates commands which represent special characters into their Unicode equivalents. Most people will want to use the standard parser.

The parser is invoked like this:
```java
InputStream is = new FileInputStream("/path/to/my/file.rtf");
IRtfSource source = new RtfStreamSource(is)
IRtfParser parser = new StandardRtfParser();
MyRtfListener listener = new MyRtfListener();
parser.parse(source, listener);
```
You provide input to the parser via a class that implements the `IRtfSource` interface. Two implementations are provided for you, `RtfStreamSource`, for reading RTF from a stream, and `RtfStringSource` for reading RTF from a string.
