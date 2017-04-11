2017-04-01-Converting HTML to PDF
=================================

- Only option that seems free and current:

https://github.com/danfickle/openhtmltopdf

- Maven details:

https://github.com/danfickle/openhtmltopdf/blob/open-dev-v1/docs/integration-guide.md


Working Prototype
-----------------

- Can highlight pdf text and copy, good(!)

- Issue with long text going off rendered pdf
- Possible to surpress new java thread?  Pops open on OSX desktop
- Need to discover how to manage text wrapping


Text Wrapping
-------------

- Fixed, wraps rather then going off page with `white-space: pre-wrap;`
- In HTML the copied text DOES NOT have new line, which is correct
- In PDF the copied text DOES have new line, which is NOT correct

- Not sure if possible to eliminate new line or at least have character represent new line visually


Missing Background Color
------------------------

- Inline code 