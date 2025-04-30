package FPAssignment1;

import java.util.List;

/**
 * Generic interface for enabling search functionality on a list of items.
 * 
 * @param <T> The type of objects this interface will be used to search through.
 * 
 * This interface is implemented by classes like EventManager to allow searching
 * for specific items (e.g., events) using user-entered keywords.
 */
public interface Searchable<T> {

    /**
     * Searches for a single item that matches the given keyword.
     * 
     * @param keyword The keyword entered by the user.
     * @return A single matching item, or null if no match is found.
     */
    T search(String keyword); 
    
    /**
     * Searches for all items that match the given keyword.
     * 
     * @param keyword The keyword entered by the user.
     * @return A list of all matching items. The list will be empty if no matches are found.
     */
    List<T> searchAll(String keyword);
}

