package com.yomo.templateapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yomo.templateapp.R;
import com.yomo.templateapp.activity.SmartcheckActivity;

import io.swagger.client.model.SmartTransaction;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionCategorizerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionCategorizerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionCategorizerFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private SmartcheckActivity smartcheckActivity;
    private SmartTransaction smartTransaction;

    private OnFragmentInteractionListener mListener;

    public TransactionCategorizerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionCategorizerFragment.
     */
    public static TransactionCategorizerFragment newInstance(int transactionIndex) {
        TransactionCategorizerFragment fragment = new TransactionCategorizerFragment();
        Bundle args = new Bundle();
        args.putInt("transaction_index", transactionIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            smartcheckActivity = (SmartcheckActivity) getActivity();
            int transactionIndex = getArguments().getInt("transaction_index");

            System.out.println("####################################################");
            System.out.println("transactionIndex = " + transactionIndex);
            System.out.println("####################################################");


            smartTransaction = smartcheckActivity.getItem(transactionIndex);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_transaction_categorizer, container, false);

        TextView transactionTitle = inflate.findViewById(R.id.transaction_title);

        transactionTitle.setText(smartTransaction.getQuestions().get(0));

        TextView yesButton = inflate.findViewById(R.id.smartcheck_card_btn_yes);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartcheckActivity.navigateToNext();
            }
        });


        return inflate;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
