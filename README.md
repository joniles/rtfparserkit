RTF Parser Kit
==============

I have often been frustrated by the lack of comprehensive support for working with RTF in Java, and the need to use RTF parsers which are incomplete and form part of larger projects whose libraries I don't want to import just to use the RTF parser. The RTF Parser Kit project is an attempt to address these points.

The idea is to provide a "kit" of components which can either be used "as-is", for example to extract plain text or HTML from an RTF file, or can be used as a component in a larger application which requires the capability to parse RTF documents.

What's currently included?
--------------------------
* Raw RTF Parser - parses RTF, sends events representing content to a listener. Performs minimal processing - you get the RTF commands and data exactly as they appear in the file.
* Standard RTF Parser - parses RTF, sends events representing content to a listener. Handles character encoding, Unicode and so on, so you don't have to.
* Document Builder RTF Parser - parses RTF and constructs a document object model via an implementation of the Document interface. You can either use the provided DefaultDocument implementation, or provide your own.  This is probably the parser you want to use.
* Text Converter - demonstrates very simple text extraction from an RTF file

What's planned?
---------------
* HTML converter
* RTF generation from the RTF document object model

That's a lot of stuff!
----------------------
Yes it is! It'll take me a while to work my way through the list of things I want to achieve, so I'd love for you to send me some code which extends what I've done or makes it better!
