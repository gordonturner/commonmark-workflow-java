package com.gordonturner.commonmark;

import com.openhtmltopdf.DOMBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.*;
import java.net.URL;
import java.util.stream.Collectors;

/**
 *
 */
public class App {
  
  public final static String MARKDOWN_EXTENSION = ".md";
  public final static String HTML_EXTENSION = ".html";
  public final static String PDF_EXTENSION = ".pdf";
  
  // https://raw.githubusercontent.com/sindresorhus/github-markdown-css/gh-pages/github-markdown.css
  public final static String GITHUB_MARKDOWN_CSS_FILE = "github-markdown.css" ;
  
  public static void main(String[] args)
  {
    String htmlFilename = convertCommonmarkToHtml(args[0]);
    convertHtmlToPdf(htmlFilename);
  }
  
  /**
   * NOTE: Does not work, struggling with `URL url = new URL(htmlFilename);`
   *
   * @param htmlFilename
   * @return
   */
  private static String convertHtml5ToPdf( String htmlFilename)
  {
    String pdfFilename = createPdfOutputFilename(htmlFilename);
    Document html5Document = null;
    
    try {
      URL url = new URL(htmlFilename);
      org.jsoup.nodes.Document documnet = Jsoup.parse(new File(url.getPath()), "UTF-8");
      html5Document = DOMBuilder.jsoup2DOM(documnet);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    try
    {
      OutputStream outputStream = new FileOutputStream(pdfFilename);
      
      try
      {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withW3cDocument(html5Document, "file://");
        builder.toStream(outputStream);
        builder.run();
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
      finally
      {
        try
        {
          outputStream.close();
        }
        catch (IOException e)
        {
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
      HtmlRenderer renderer = HtmlRenderer.builder().build();
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
   * @return
   */
  private static String createGithubMarkdownHtmlHeader()
  {
    return createHtmlHeader(GITHUB_MARKDOWN_CSS_FILE);
  }
  
  /**
   * @param cssFileName
   * @return
   */
  private static String createHtmlHeader( String cssFileName )
  {
    InputStreamReader inputStreamReader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(cssFileName));
    String cssFileNameContents = new BufferedReader(inputStreamReader).lines().collect(Collectors.joining("\n"));
    
    return "<!DOCTYPE html>\n" +
      "<html>\n" +
      "<head>\n" +
      "<style>\n" +
      cssFileNameContents +
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
