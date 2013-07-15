RTF Parser Kit
==============

I haver often been frustrated by the lack of comprehensive support for working with RTF in Java.
The RTF Parser Kit project is an attempt to address this omission.

The idea is to provide a "kit" of components which can either be used "as-is", for example to extract plain text or HTML from an RTF file, or can be used as a component in a larger application.

What's currently included?
--------------------------
* Raw RTF Parser - parses RTF, sends events representing content to a listener. Performs minimal processing - you get the RTF commands and data exactly as they appear in the file.
* Standard RTF Parser - parses RTF, sends events representing content to a listener. Handles character encoding, Unicode and so on, so you don't have to. This is probably the parser you want to use.
* Text Converter - demonstrates very simple text extraction from an RTF file

What's planned?
---------------
* HTML converter
* Parsing to an RTF document object model
* RTF generation from an RTF document object model

That's a lot of stuff!
----------------------
Yes it is! It'll take me a while to work my way through the list of things I want to achieve, so I'd love for you to send me some code which extends what I've done or makes it better!
