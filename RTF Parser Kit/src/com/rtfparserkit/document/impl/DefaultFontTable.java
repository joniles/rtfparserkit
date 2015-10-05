
package com.rtfparserkit.document.impl;

import java.util.HashMap;

import com.rtfparserkit.document.Font;
import com.rtfparserkit.document.FontTable;

public class DefaultFontTable implements FontTable
{

   private final HashMap<Integer, DefaultFont> fonts;

   public DefaultFontTable()
   {
      fonts = new HashMap<Integer, DefaultFont>();
   }

   @Override
   public void addFont(int id, String name, String alternativeName, String fileName, FontFamily fontFamily)
   {
      fonts.put(id, new DefaultFont(name));
   }

   @Override
   public int countFonts()
   {
      return fonts.size();
   }

   @Override
   public Font fontFor(int id)
   {
      return fonts.get(id);
   }
}
