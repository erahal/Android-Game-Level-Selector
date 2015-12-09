package com.epollomes.genericlevelselector.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.epollomes.genericlevelselector.R;
import com.epollomes.genericlevelselector.widgets.CustomButton;
import com.epollomes.genericlevelselector.widgets.IOnButtonClicked;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IOnPageFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SinglePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SinglePageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "XXX";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout linearLayout;

    private IOnPageFragmentInteractionListener mListener;

    public SinglePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SinglePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SinglePageFragment newInstance(String param1, String param2) {
        SinglePageFragment fragment = new SinglePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_page, container, false);
        linearLayout = (LinearLayout) rootView.findViewById(R.id.root_linear_layout);
        loopOnChildrenViews(linearLayout);
        setButtonsListeners(linearLayout);
        return rootView;

    }

    int counter = 0;

    private void setButtonsListeners(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View v = viewGroup.getChildAt(i);
            if (v instanceof CustomButton) {
                ((CustomButton) v).mListener2 = new IOnButtonClicked() {
                    @Override
                    public void onLoadLevel() {
                        Toast.makeText(getActivity(), "Loading Level: " + ((CustomButton) v).getTextView().getText(), Toast
                                .LENGTH_SHORT).show();
                    }
                };
            } else if (v instanceof ViewGroup) {
                setButtonsListeners((ViewGroup) v);
            }
        }
    }

    private void loopOnChildrenViews(ViewGroup viewGroup) {

        int getPageCounter = Integer.parseInt(mParam1);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setText("" + ((getPageCounter - 1) * 15 + counter + 1));
                counter += 1;
            } else if (v instanceof ViewGroup) {
                loopOnChildrenViews((ViewGroup) v);
            }
        }
    }

    private void resetAllViewsScales() {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View v = linearLayout.getChildAt(i);
            if (v instanceof CustomButton) {
                (v).setScaleX(1.0f);
                (v).setScaleY(1.0f);
            }
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPageFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnPageFragmentInteractionListener) {
            mListener = (IOnPageFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IOnPageFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface IOnPageFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPageFragmentInteraction(Uri uri);
    }
}
