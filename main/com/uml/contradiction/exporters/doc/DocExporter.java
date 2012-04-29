package com.uml.contradiction.exporters.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.uml.contradiction.engine.model.GeneralResult;
import com.uml.contradiction.exporters.Exporter;

public class DocExporter implements Exporter {

	@Override
	public void export(GeneralResult generalResult) throws Exception {
		File file = new File("resources"+File.separator+"sample.doc");
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		HWPFDocument doc = new HWPFDocument(fs);
		Range range = doc.getRange();
		Paragraph paragraph0 = range.insertAfter(new ParagraphProperties(), 0);
		CharacterRun run0 = paragraph0.insertAfter("gleb");
			Paragraph paragraph = run0.insertAfter(new ParagraphProperties(), 0);
			CharacterRun run = paragraph.insertAfter("awesome");
			run0 = run;
		
		/**
        // Get the Ramge object encapsulating the text for the entire document. 
        Range range = doc.getRange(); 

        // Add a new Pargarph - it will be centered and the spacing after 
        // will be 200 units (not too sure what the units are so check in the 
        // javadoc). 
        ParagraphProperties pp = new ParagraphProperties();
        pp.setJustification((byte)2);
        pp.setSpacingAfter(200);
        Paragraph par1 = range.insertAfter(pp, 0); 
        par1.setSpacingAfter(200); 
        par1.setJustification((byte) 2); 
        // justification: 0=left, 1=center, 2=right, 3=left and right 

        // Insert some text into the Paragraph created in the step above. It 
        // contains just a single word - 'one' - and the size of the fint has 
        // been increased. 
        CharacterRun run1 = par1.insertAfter("one"); 
        run1.setFontSize(36); 

        // paragraph with bold typeface 
        Paragraph par2 = run1.insertAfter(new ParagraphProperties(), 0); 
        par2.setSpacingAfter(200); 
        CharacterRun run2 = par2.insertAfter("two two two two two two " + 
                "two two two two two two two"); 
        run2.setBold(true); 

        // Paragraph three. This contains code showing you how to add more 
        // than a single sentence of text to a Paragraph. Further, it 
        // demonstrates that if you have a single sentence containing text 
        // that should have different treatements applied to specific word - 
        // imagine for example that a single word should be italicised - then 
        // you will need to create two or more CharacterRun(s) to achieve 
        // this. 
        Paragraph par3 = run2.insertAfter(new ParagraphProperties(), 0); 
        par3.setFirstLineIndent(200); 
        par3.setSpacingAfter(200); 
        CharacterRun run3 = par3.insertAfter("three three three three " + 
                "three three three three three three three three three " + 
                "three three three three three three three three three " + 
                "three three three three three three three three three " + 
                "three three three three three three."); 
        run3.setItalic(true); 
        CharacterRun run4 = run3.insertAfter(" With luck, this will be " + 
                "inserted after all of those threes and show how to add " + 
                "more than one CharacterRun to a Paragraph."); 
        CharacterRun run5 = run4.insertAfter("If the treatment differs " + 
                "in anyway"); 
        //run5.setItalic(false); 
        CharacterRun run6 = run5.insertAfter(" in "); 
        //run6.setBold(true); 
        CharacterRun run7 = run6.insertAfter("the sentence then you will " + 
                "need to create and add a"); 
        CharacterRun run8 = run7.insertAfter(" new "); 
        //run7.setItalic(true); 
        CharacterRun run9 = run8.insertAfter("Ch");
        **/
		OutputStream out = new FileOutputStream(new File("result"));
		doc.write(out);
		out.flush();
		out.close();
	}


}
