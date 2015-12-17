package com.stanfy.helium.handler.codegen.objectivec.entity.file

import java.util.ArrayList

/**
 * Created by ptaykalo on 8/17/14.
 * Simple block that know how to serialize ObjC Class Implementation
 */
public class ObjCImplementationFileSourcePart(val filename: String) : ObjCSourcePart {

  private val importSourceParts = ArrayList<ObjCImportPart>()
  private val bodySourceParts = ArrayList<ObjCSourcePart>()

  /**
  Adds specified source part to the top part (before @implementation)
   */
  public fun addImportSourcePart(sourcePart: ObjCImportPart) {
    importSourceParts.add(sourcePart)
  }

  /**
  Adds specified source part to the central part (inside @implementation)
   */
  public fun addBodySourcePart(sourcePart: ObjCSourcePart) {
    bodySourceParts.add(sourcePart)
  }

  override fun asString(): String {
    val bld = StringBuilder()
    bld.append("#import \"").append(filename).append(".h\"\n")
    for (sourcePart in importSourceParts) {
      bld.append(sourcePart.asString()).append("\n")
    }
    bld.append("@implementation ").append(filename).append("\n")
    for (sourcePart in bodySourceParts) {
      bld.append(sourcePart.asString()).append("\n")
    }
    bld.append("@end")
    return bld.toString()

  }
}
