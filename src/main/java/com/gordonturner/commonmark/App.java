package com.gordonturner.commonmark;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
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
    
    try
    {
      FileReader fileReader = new FileReader(args[0]);
      FileWriter fileWriter = new FileWriter(createHtmlOutputFilename(args[0]));
      
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
   * @param inputMarkdownFilename
   * @return
   */
  private static String createPdfOutputFilename(String inputMarkdownFilename)
  {
    if(inputMarkdownFilename.endsWith(MARKDOWN_EXTENSION))
    {
      return inputMarkdownFilename.substring(0, inputMarkdownFilename.lastIndexOf(MARKDOWN_EXTENSION)) + HTML_EXTENSION;
    }
    else
    {
      return inputMarkdownFilename + "." + PDF_EXTENSION;
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
