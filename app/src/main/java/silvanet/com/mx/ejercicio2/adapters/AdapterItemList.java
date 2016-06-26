package silvanet.com.mx.ejercicio2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.squareup.picasso.Picasso;
import silvanet.com.mx.ejercicio2.R;
import silvanet.com.mx.ejercicio2.model.ModelItem;

/**
 * Created by LuisAlfredoSilva on 24/06/2016.
 */
public class AdapterItemList extends ArrayAdapter<ModelItem> {
    private final String url1="https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Biblioteca_Central_UNAM_M%C3%A9xico.jpg/250px-Biblioteca_Central_UNAM_M%C3%A9xico.jpg";
    private final String url2="https://www.unam.mx/sites/default/files/images/menu/library-345273_1280.jpg";
    public AdapterItemList(Context context, List<ModelItem> objects) {
        super(context, 0, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        }
        TextView txtItemDescription= (TextView) convertView.findViewById(R.id.txtItemDescription);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtItemTitle);
        ImageView img = (ImageView) convertView.findViewById(R.id.row_image_view);
        ModelItem modelItem=getItem(position);
        Picasso.with(getContext()).load(modelItem.resourceId==R.drawable.ic_action_hardware_cast_connected?
                url1:url2).into(img);

        txtTitle.setText(modelItem.item);
        txtItemDescription.setText(modelItem.description);
        return convertView;
    }
}

