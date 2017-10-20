# MultiTypeAdapter

## What is it?

This is an adapter to be used with the Android RecyclerView. This adapter allows for multiple item 
types to be shown in a RecyclerView.

## How does it work?

There are 4 main components:
- MultiTypeAdapter
- TypeAdapter
- TypeAdapterFactory
- Items

<b>MultiTypeAdapter:</b> This extends the RecyclerView.Adapter and takes in a list of TypeAdapters. You use
this class to set/update the adapter data.

<b>TypeAdapter:</b> This is an interface that mimics the required methods typically overridden in a
RecyclerView.Adapter.

<b>TypeAdapterFactory:</b> Creates custom implementations of the TypeAdapter using a functional approach.

<b>Items:</b> This is a simple wrapper around a list of Objects. Any data you want to put into the
adapter can easily be constructed using Items.

## How do I get it?

#### Gradle:
````
compile 'com.iheartradio.android:mt-adapter:0.1.0'
````

## Usage:

#### Create a standard ViewHolder:
````
public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView mText;

    public MyViewHolder(View itemView) {
        super(itemView);
        mText = (TextView) itemView.findViewById(R.id.text);
    }

    public static MyViewHolder create(final ViewGroup parent) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    public void bindData(final String data) {
        mText.setText(data);
    }
}
````

#### Create TypeAdapter:
````
TypeAdapter myTypeAdapter = TypeAdapterFactory.create(String.class, 
                                                            MyViewHolder::create,
                                                            MyViewHolder::bindData);
````
In this case we are creating a type adapter that maps strings to our MyViewHolder class.
The first param is the data type, second is a method to create the viewholder, and third a 
method to bind the data from the first param to the viewholder.

Instead of using the TypeAdapterFactory we can just implement TypeAdapter ourselves like so:

````
public class MyTypeAdapter extends TypeAdapter<String, MyViewHolder> {

    @Override
    public boolean isMyData(Object data) {
        return data instanceof String;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent) {
        return MyViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder,
                                 final String data,
                                 final List<Object> payloads) {
        viewHolder.bind(data);
    }
}
````


#### Create MultiTypeAdapter:

````
MultiTypeAdapter adapter = new MultiTypeAdapter(myTypeAdapter);
````
We create the MultiTypeAdapter by passing our TypeAdapter into the constructor. The adapter
is now going to map String data passed to it to the specified viewholder.

#### Set Data:
````
Items items = Items();
items.add("Item 1");
items.add("Item 2");
items.add("Item 3");
adapter.setData(items.get());

````


## Changelog
- First release

## License

````
Copyright 2017 iHeartRadio

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````

