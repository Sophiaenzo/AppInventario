/*
 * The MIT License
 *
 * Copyright 2023 wilson.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package modelDominio;

import java.io.Serializable;

/**
 *
 * @author wilson
 */
public class R999usu implements Serializable{
     private static final long serialVersionUID = 123456789L;
       private String CODUSU;
       private String NOMUSU;

    public R999usu(String NOMUSU) {
        this.NOMUSU = NOMUSU;
    }

    public R999usu(String CODUSU, String NOMUSU) {
        this.CODUSU = CODUSU;
        this.NOMUSU = NOMUSU;
    }


    public String getCODUSU() {
        return CODUSU;
    }

    public void setCODUSU(String CODUSU) {
        this.CODUSU = CODUSU;
    }

    public String getNOMUSU(String usuario) {
        return NOMUSU;
    }

    public void setNOMUSU(String NOMUSU) {
        this.NOMUSU = NOMUSU;
    }
     
     
     
}
