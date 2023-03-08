package main;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class Main
{
	public static void addMetadata(PDDocument doc, String author, String title, String creator, String subject, String keywords, String producer, Calendar date)
	{		
		// Creating the PDDocumentInformation object
		PDDocumentInformation pdd = doc.getDocumentInformation() ;
		pdd.setAuthor(author) ;
		pdd.setTitle(title) ;
		pdd.setCreator(creator) ;
		pdd.setSubject(subject) ;
		pdd.setCreationDate(date) ;
		pdd.setKeywords(keywords) ;
		pdd.setProducer(producer) ;
	}
	public static void addRect(PDPageContentStream contentStream, int x, int y, int w, int h, Color color, boolean fill) throws IOException
	{		
		// drawing a rectangle
		contentStream.setNonStrokingColor(color) ;  
	    contentStream.addRect(x, y, w, h) ;
	    if (fill) { contentStream.fill() ; }
	    else { contentStream.stroke() ; }
		System.out.println("Rectangle inserted Successfully.");  
	}
	public static void addImage(PDPageContentStream contentStream, PDImageXObject pdImage, int x, int y) throws IOException
	{
		contentStream.drawImage(pdImage, x, y);
		System.out.println("Image inserted Successfully.");  
	}
	public static void updatingExistingPDF() throws IOException
	{
		//Loading an existing document   
		File file = new File("C:\\Users\\SALVIO.005867\\Desktop\\my_doc.pdf");  
		PDDocument doc = PDDocument.load(file);  
		    
		//Retrieving the page  
		PDPage page = doc.getPage(0);  
		    
		//Creating PDImageXObject object  
		PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\SALVIO.005867\\Desktop\\Logo-Lua.png", doc);  
		    
		//creating the PDPageContentStream object  
		PDPageContentStream contents = new PDPageContentStream(doc, page);  
		
		//Drawing the image in the PDF document  
		contents.drawImage(pdImage, 250, 300);  
		
		System.out.println("Image inserted Successfully.");  
		    
		// closing the PDPageContentStream object  
		contents.close();         
		    
		// saving the document  
		doc.save("C:\\Users\\SALVIO.005867\\Desktop\\my_doc.pdf") ;
		    
		// closing the document  
		doc.close();  
	}
	public static void createPDF(String path, String name) throws IOException
    {
		// creating PDF document object 
		PDDocument doc = new PDDocument() ;  
		
		// adding a page
		PDPage page1 = new PDPage() ;
		doc.addPage(page1) ;

		// saving the document
		doc.save(path + name) ;
		     
		System.out.println("PDF created") ;  
		// closing the document  
		doc.close() ;
    }
	public static void createRascunhoAlvara(String path, String name) throws IOException
    {
		// creating PDF document object 
		PDDocument doc = new PDDocument() ;  
		
		// adding a page
		PDPage page1 = new PDPage() ;
		doc.addPage(page1) ;

		// adding content to the document
		PDPageContentStream contentStream = new PDPageContentStream(doc, page1) ;
		
		// adding an image
		PDImageXObject logoLUA = PDImageXObject.createFromFile("./Rascunho.png", doc);
		addImage(contentStream, logoLUA, 0, 0) ;
		
	    // writing text
		contentStream.beginText() ;
		contentStream.newLineAtOffset(20, 450) ;
		contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14) ;
		contentStream.setLeading(14.5f) ;
		contentStream.showText("Meu texto") ;
		contentStream.newLine() ;
		contentStream.showText("Texto 2") ;
		contentStream.endText() ;
		
		// closing the content Stream
		contentStream.close() ;
				
		// adding the metadata
		Calendar date = new GregorianCalendar(2022, 11, 10) ;
		addMetadata(doc, "Sálvio Jr", "Rascunho alvará", "Sálvio Jr", "Rascunho de alvará", "Alvará", "Sálvio Jr", date) ;
		   
		// saving the document
		doc.save(path + name) ;
		     
		System.out.println("PDF created") ;  
		// closing the document  
		doc.close() ;
    }
	public static void main(String[] args)
	{
		try
		{
			createRascunhoAlvara("./", "RascunhoAlvara.pdf") ;
		}
		catch (IOException e)
		{
			e.printStackTrace() ;
		}
    }
}
