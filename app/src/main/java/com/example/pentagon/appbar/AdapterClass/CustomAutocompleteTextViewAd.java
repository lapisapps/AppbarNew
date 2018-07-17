package com.example.pentagon.appbar.AdapterClass;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.pentagon.appbar.DataClass.DataTag;
import com.example.pentagon.appbar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshay on 1/2/15.
 */
public class CustomAutocompleteTextViewAd extends ArrayAdapter<DataTag> {

    Context context;
    int resource, textViewResourceId;
    List<DataTag> items, tempItems, suggestions;

    public CustomAutocompleteTextViewAd(Context context, int resource, int textViewResourceId, List<DataTag> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<DataTag>(items); // this makes the difference.
        suggestions = new ArrayList<DataTag>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_autocomplete, parent, false);
        }
        DataTag people = items.get(position);
        if (people != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            if (lblName != null)
                lblName.setText(people.getTag());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((DataTag) resultValue).getTag();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (DataTag people : tempItems) {
                    if (people.getTag().toLowerCase().contains(constraint.toString().toLowerCase())||people.getTagid().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            suggestions.add(people);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<DataTag> filterList = (ArrayList<DataTag>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (DataTag people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };
}