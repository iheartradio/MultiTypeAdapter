# MultiTypeAdapter - Internal

###What is it?
This is an updated and renamed version the HetergeneousAdapter currently being used in the flagship app. 
This is a private internal repo to help get feedback on the currently feature, functionality, and implementation.

###Why was it renamed?
The first name was pretty long and bad... Also, some of the names such as "Binder" gave off the 
wrong impression as to what the class should be doing.

###What has changed?
- DataSet interface is completely removed. It now uses the object class to represent data.
- MultiTypeDataHelper.java has been added to help in assisting the creation of data for the adapter.
- The Android DiffUtil class is now integrated into the adapter allowing for more fine grained data 
updates as well as pleasant animations. Optional methods in the TypeAdapter (formally HeterogeneousBinder) 
have been added to allow for this functionality.
- Drag and Swipe functionality has been added to the adapter with the helper class SimpleItemTouchHelperCallback
- Span lookup for GridLayoutManager is automatically handled in the adapter with simple overriding 
functionality by implementing the SpanHandler interface 

