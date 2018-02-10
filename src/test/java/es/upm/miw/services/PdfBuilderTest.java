package es.upm.miw.services;

import org.junit.Test;

import es.upm.miw.utils.Barcode;

public class PdfBuilderTest {

    @Test
    public void testBuildLabel24a() {
        Barcode barcode = new Barcode();
        PdfTag24Builder pdf = new PdfTag24Builder("/test/label");
        for (int i = 10; i < 41; i++) {
            pdf.addTag24("Etiqueta " + i, "8400000150" + i + barcode.ean13ControlCodeCalculator("8400000150" + i));
        }
        pdf.build();
    }

}
