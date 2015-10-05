
package com.rtfparserkit.document.impl;

import java.util.HashMap;
import java.util.Iterator;

import com.rtfparserkit.document.Font;
import com.rtfparserkit.document.FontTable;

public class DefaultFontTable implements FontTable
{
   private final HashMap<Integer, Font> fonts;

   public DefaultFontTable()
   {
      fonts = new HashMap<Integer, Font>();
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

   @Override
   public Iterator<Font> iterator()
   {
      return fonts.values().iterator();
   }
}
