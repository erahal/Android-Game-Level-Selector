# Android-Game-Level-Selector
* This is a free Android Level Selector that you can use in your games
* The selector is in landscape mode and is also has proper layouts for tablet support
* Its has support for custom fonts for the level's button numbering

![alt tag](https://github.com/erahal/Android-Game-Level-Selector/blob/master/GenericLevelSelector/levelSelector.gif)


##Usage Rights
* You can use this project for free, no credit is required.. totally free.
* If you feel like helping, try some of my apps under epollomes on the playstore, [Visit epollomes ] (https://goo.gl/f5YBwy)

## Techncial Description
* The project is a single Activity that contains a main fragment LevelSelectorFragment.
* It is page on android's View Pager
* You can either use the complete activity, or use the fragment in your own project container

##Changing the Level Selector Settings
### How to change the number of levels in a single page? 
* The default settings is a 5 Columns X 3 Rows, to modify the page, you need to modify the number of widgets in the layout **fragment_single_page.xml**

### How to change the number of pages?
* To modify the number of pages, you need to modify the value of the constant **NUMBER_OF_PAGES**, inside the **LevelSelectorFragment.java** class of the fragment.

### Caution when modifying
* When modifying a layout make sure you modify the tablet layout also to have the modification on the tablet's layouts
* The code has been written to dynamically modify the values when you change the level selector's configurations ( nbre of pages, etc..)

