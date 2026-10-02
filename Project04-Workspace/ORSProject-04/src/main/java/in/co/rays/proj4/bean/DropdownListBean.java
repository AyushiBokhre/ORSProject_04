package in.co.rays.proj4.bean;

/**
 * DropdownListBean interface provides methods to get key and value
 * for displaying bean data in dropdown lists.
 *
 * @author Aayushi
 * @version 1.0
 */
public interface DropdownListBean {

	/**
	 * Returns the key of the dropdown list item.
	 *
	 * @return key as String
	 */
	public String getKey();

	/**
	 * Returns the value of the dropdown list item.
	 *
	 * @return value as String
	 */
	public String getValue();

}

