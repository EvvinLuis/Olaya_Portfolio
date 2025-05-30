package com.example.olayaportfolio;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import utils.CustomTypefaceSpan;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView textView = view.findViewById(R.id.textview);

        String text = "I'm Evvin Luis Olaya, Mobile Application Developer from Philippines.";
        SpannableString spannableString = new SpannableString(text);

        int start = text.indexOf("Evvin Luis Olaya");
        int end = start + "Evvin Luis Olaya".length();

        spannableString.setSpan(new ForegroundColorSpan(Color.rgb(67, 184, 217)), start, end, 0);

        Typeface customTypeface = ResourcesCompat.getFont(requireContext(), R.font.heavitas);

        spannableString.setSpan(new CustomTypefaceSpan("", customTypeface), start, end, 0);

        textView.setText(spannableString);

        Button knowMoreButton = view.findViewById(R.id.kmbutton);

        knowMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });


        return view;
    }

    private void openDrawer() {
        DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
