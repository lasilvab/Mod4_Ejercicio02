package silvanet.com.mx.ejercicio2.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import silvanet.com.mx.ejercicio2.R;

/**
 * Created by LuisAlfredoSilva on 24/06/2016.
 */
public class FragmentProfile extends Fragment {
    private ImageView imgProfile;
    private boolean change=true;

    public static FragmentProfile newInstance(String name)
    {
        FragmentProfile f = new FragmentProfile();
        Bundle b = new Bundle();
        b.putString("user_key",name);
        f.setArguments(b);
        return f;
    }

    public void changeImage()
    {
        imgProfile.setImageResource(change?R.drawable.ic_action_hardware_cast_connected: R.drawable.ic_communication_call);
        change=!change;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        imgProfile = (ImageView) view.findViewById(R.id.imgProfile);
        TextView txt = (TextView) view.findViewById(R.id.txtUserFragment);
        Bundle bundle=getArguments();
        String user ;
        if(bundle!=null)
            user=bundle.getString("user_key");
        else
            user= "XML inflate";

        txt.setText(user);
        return view;
    }
}

