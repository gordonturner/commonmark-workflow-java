package com.gordonturner.commonmark;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args)
  {
    
    try
    {
      FileReader fileReader = new FileReader(args[0]);
      FileWriter fileWriter = new FileWriter(args[0] + ".html");
      
      Parser parser = Parser.builder().build();
      Node document = parser.parseReader(fileReader);
      HtmlRenderer renderer = HtmlRenderer.builder().build();
      String rendered = renderer.render(document);
      
      System.out.println( rendered );
      fileWriter.write(rendered);
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
}
