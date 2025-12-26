package dev.taitd.replication.util;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Constant {

  private Constant() {
    // Nothing
  }

  protected static final List<Locale> LOCALES = Arrays.asList(
      Locale.forLanguageTag("ja"),
      Locale.forLanguageTag("en"));
}