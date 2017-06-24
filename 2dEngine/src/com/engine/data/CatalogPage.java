package com.engine.data;

public class CatalogPage<E>
{
	@SuppressWarnings("unchecked")
	private CatalogItem<E>[] items = new CatalogItem[16];
	
	private int numItems = 0;
	
	public CatalogPage()
	{
		
	}
	
	public void addItem(CatalogItem<E> item)
	{
		numItems++;
		
		items[numItems - 1] = item;
	}
	
	public CatalogItem<E> getItem(int index)
	{
		return items[index];
	}

	/**
	 * @return the items
	 */
	public CatalogItem<E>[] getItems()
	{
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(CatalogItem<E>[] items)
	{
		this.items = items;
	}

	/**
	 * @return the numItems
	 */
	public int getNumItems()
	{
		return numItems;
	}

	/**
	 * @param numItems the numItems to set
	 */
	public void setNumItems(int numItems)
	{
		this.numItems = numItems;
	}
	
	
}