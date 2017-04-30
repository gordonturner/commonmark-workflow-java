package com.gordonturner.commonmark;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.io.FileUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;
import java.util.stream.Collectors;

/**
 *
 */
public class App {
  
  public final static String MARKDOWN_EXTENSION = ".md";
  public final static String HTML_EXTENSION = ".html";
  public final static String PDF_EXTENSION = ".pdf";
  public final static String DOC_EXTENSION = ".doc";
  
  // https://raw.githubusercontent.com/sindresorhus/github-markdown-css/gh-pages/github-markdown.css
  public final static String GITHUB_MARKDOWN_CSS_FILE = "github-markdown.css";
  public final static String GITHUB_MARKDOWN_CSS_CUSTOM_FILE = "github-markdown-custom.css";
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
  
    System.out.println(args[0]);
    System.out.println(args[1]);
    
    if(args.length != 2) {
      printUsage();
    }
    {
      if("-p".equals(args[0]))
      {
        String htmlFilename = convertCommonmarkToHtml(args[1]);
        convertHtmlToPdf(htmlFilename);
      }
      else if("-d".equals(args[0]))
      {
        String htmlFilename = convertCommonmarkToHtml(args[1]);
        convertHtmlToDoc(htmlFilename);
      }
      else
      {
        printUsage();
      }
    }
  }
  
  /**
   *
   */
  private static void printUsage()
  {
    System.out.println("Invalid number of args");
    System.out.println("Usage for pdf: java -jar ./commonmark-workflow-2.0-jar-with-dependencies.jar -p ./readme.md");
    System.out.println("Usage for doc: java -jar ./commonmark-workflow-2.0-jar-with-dependencies.jar -d ./readme.md");
  }
  
  /**
   * @param htmlFilename
   * @return
   */
  private static String convertHtmlToPdf( String htmlFilename)
  {
    String pdfFilename = createPdfOutputFilename(htmlFilename);
    
    try
    {
      OutputStream outputStream = new FileOutputStream(pdfFilename);
      
      try {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withFile(new File(htmlFilename));
        
        builder.toStream(outputStream);
        builder.run();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        try {
          outputStream.close();
        } catch (IOException e) {
          // Do nothing
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return pdfFilename;
  }
  
  /**
   * @param htmlFilename
   * @return
   */
  private static String convertHtmlToDoc( String htmlFilename)
  {
    String docFilename = createDocOutputFilename(htmlFilename);
  
    WordprocessingMLPackage wordMLPackage = null;
    try
    {
      // Default to letter and portrait
      wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.LETTER, false);
    }
    catch (InvalidFormatException e)
    {
      e.printStackTrace();
    }
  
    XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
  
    String baseURL = "./";
  
    
    
    String stringFromFile = null;
    try
    {
      stringFromFile = FileUtils.readFileToString(new File(htmlFilename), "UTF-8");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    String unescaped = stringFromFile;
    
//    if (stringFromFile.contains("&lt;/") )
//    {
//      unescaped = StringEscapeUtils.unescapeHtml(stringFromFile);
//    }
    
    OutputStream fos = null;
    try
    {
      fos = new ByteArrayOutputStream();
      wordMLPackage.getMainDocumentPart().getContent().addAll( XHTMLImporter.convert(unescaped, baseURL) );
      
      HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
      htmlSettings.setWmlPackage(wordMLPackage);
      Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);
      Docx4J.toHTML(htmlSettings, fos, Docx4J.FLAG_EXPORT_PREFER_NONXSL);
      wordMLPackage.save(new File (docFilename));
    }
    catch (Docx4JException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        fos.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  
    return docFilename;
  }
  
  /**
   * @param commonmarkFilename
   * @return
   */
  private static String convertCommonmarkToHtml( String commonmarkFilename)
  {
    String htmlFilename = createHtmlOutputFilename(commonmarkFilename);
    
    try
    {
      FileReader fileReader = new FileReader(commonmarkFilename);
      FileWriter fileWriter = new FileWriter(htmlFilename);
    
      Parser parser = Parser.builder().build();
      Node document = parser.parseReader(fileReader);
      HtmlRenderer renderer = HtmlRenderer.builder().escapeHtml(true).build();
      String rendered = renderer.render(document);
    
      String renderedStyled = createGithubMarkdownHtmlHeader() + rendered + createHtmlFooter();
    
      System.out.println( renderedStyled );
      fileWriter.write(renderedStyled);
      fileWriter.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    
    return htmlFilename;
  }
  
  /**
   * @param inputMarkdownFilename
   * @return
   */
  private static String createHtmlOutputFilename(String inputMarkdownFilename)
  {
    if(inputMarkdownFilename.endsWith(MARKDOWN_EXTENSION))
    {
      return inputMarkdownFilename.substring(0, inputMarkdownFilename.lastIndexOf(MARKDOWN_EXTENSION)) + HTML_EXTENSION;
    }
    else
    {
      return inputMarkdownFilename + "." + HTML_EXTENSION;
    }
  }
  
  /**
   * @param inputHtmlFilename
   * @return
   */
  private static String createPdfOutputFilename(String inputHtmlFilename)
  {
    if(inputHtmlFilename.endsWith(HTML_EXTENSION))
    {
      return inputHtmlFilename.substring(0, inputHtmlFilename.lastIndexOf(HTML_EXTENSION)) + PDF_EXTENSION;
    }
    else
    {
      return inputHtmlFilename + "." + PDF_EXTENSION;
    }
  }
  
  /**
   * @param inputHtmlFilename
   * @return
   */
  private static String createDocOutputFilename(String inputHtmlFilename)
  {
    if(inputHtmlFilename.endsWith(HTML_EXTENSION))
    {
      return inputHtmlFilename.substring(0, inputHtmlFilename.lastIndexOf(HTML_EXTENSION)) + DOC_EXTENSION;
    }
    else
    {
      return inputHtmlFilename + "." + DOC_EXTENSION;
    }
  }
  
  /**
   * @return
   */
  private static String createGithubMarkdownHtmlHeader()
  {
    return createHtmlHeader(GITHUB_MARKDOWN_CSS_FILE, GITHUB_MARKDOWN_CSS_CUSTOM_FILE);
  }
  
  /**
   * @param cssFileName
   * @return
   */
  private static String createHtmlHeader( String... cssFilenames )
  {
    String htmlHeader = "<!DOCTYPE html>\n" +
      "<html>\n" +
      "<head>\n" +
      "<style>\n";
    
    for(String cssFilename : cssFilenames)
    {
      InputStreamReader inputStreamReader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(cssFilename));
      String cssFilenameContent = new BufferedReader(inputStreamReader).lines().collect(Collectors.joining("\n"));
      htmlHeader += cssFilenameContent;
    }
 
    return htmlHeader +
      "</style>\n" +
      "</head>\n" +
      "<body class='markdown-body'>\n";
  }
  
  /**
   * @return
   */
  private static String createHtmlFooter()
  {
    return "</body>\n" +
      "</html> ";
  }
}
