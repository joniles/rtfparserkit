package com.rtfparserkit.utils;

import com.rtfparserkit.parser.RtfListenerAdaptor;
import com.rtfparserkit.rtf.Command;

import java.util.HashMap;
import java.util.Map;

public abstract class ImageListener extends RtfListenerAdaptor
{
   public abstract void handleImageData(Map<String, Object> data);

   public void processGroupStart()
   {
      ++groupDepth;
   }

   /**
    * Write a group end tag.
    */
   @Override
   public void processGroupEnd()
   {
      --groupDepth;
      if (pictData != null && groupDepth < pictGroupDepth)
      {
         handleImageData(pictData);
         pictData = null;
      }
   }

   public void processCommand(Command command, int parameter, boolean hasParameter, boolean optional)
   {
      if (pictData != null)
      {
         Integer value = hasParameter ? Integer.valueOf(parameter) : null;
         pictData.put(command.getCommandName(), value);
      }
      else
      {
         if (command == Command.pict)
         {
            pictGroupDepth = groupDepth;
            pictData = new HashMap<String, Object>();
         }
      }
   }

   public void processString(String string)
   {
      if (pictData != null)
      {
         pictData.put("data", string);
      }
   }

   private int groupDepth;
   private int pictGroupDepth;
   private Map<String, Object> pictData;
}
