package org.jboss.windup.util.furnace;

import java.util.function.Predicate;

/**
 * Filters filenames by file extension.
 *
 */
public class FileExtensionFilter implements Predicate<String>
{
    private final String extension;

    /**
     * Only accept names that end with "." + extension.
     */
    public FileExtensionFilter(String extension)
    {
        super();
        this.extension = extension;
    }

    @Override
    public boolean test(String name)
    {
        return name.endsWith("." + extension);
    }
}
