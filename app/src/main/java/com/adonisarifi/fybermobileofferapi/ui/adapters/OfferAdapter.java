package com.adonisarifi.fybermobileofferapi.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adonisarifi.fybermobileofferapi.R;
import com.adonisarifi.fybermobileofferapi.model.OfferType;
import com.adonisarifi.fybermobileofferapi.model.Offers;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.CommentViewHolder> {


    private static final String TAG = OfferAdapter.class.getSimpleName();
    private Context myContext;
    private List<Offers> resultResponsOffers = new ArrayList<>();

    private static DataObserverListener dataObserverListener;


    private ItemClickListenerOffer itemClickListener;

    public OfferAdapter(Context myContext, ItemClickListenerOffer itemClickListener,
                        final DataObserverListener dataObserverListener) {
        this.myContext = myContext;
        this.itemClickListener = itemClickListener;
        this.dataObserverListener = dataObserverListener;

    }

    @Override
    public void onViewRecycled(CommentViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return resultResponsOffers == null ? 0 : resultResponsOffers.size();
    }


    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_offer
                , parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, int position) {
        try {
            final Offers responsData = resultResponsOffers.get(position);
            List<OfferType> offerType = responsData.getOffer_types();
            if (responsData != null) {

                holder.textView_offer_title.setText(responsData.getTitle());
                holder.textView_item_offer_required_actions.setText(responsData.getTeaser());
                Glide.with(myContext).load(responsData.getThumbnail().getHires()).asBitmap().into(holder.imageView_offer_thumbnail);
                holder.textView_payout.setText(String.valueOf(responsData.getPayout()));
                String concatOfferTypes = "";
                for (OfferType otypes : offerType) {
                    concatOfferTypes += otypes.getReadable() + " ";
                }
                holder.textView_item_offer_types.setText(concatOfferTypes);

                holder.setClickListener(new ItemClickListenerOffer() {
                    @Override
                    public void onClickOffer(Offers offer) {
                        itemClickListener.onClickOffer(responsData);
                    }
                });
            }
        } catch (Exception e) {
            e.getMessage();
        }


    }


    public void addItem(Offers addItem) {
        resultResponsOffers.add(addItem);
        dataObserverListener.onDataObserver(resultResponsOffers.size());
    }


    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        this.dataObserverListener.onDataObserver(resultResponsOffers.size());
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListenerOffer clickListener;

        @Bind(R.id.imageView_offer_thumbnail)
        ImageView imageView_offer_thumbnail;

        @Bind(R.id.textView_offer_title)
        TextView textView_offer_title;

        @Bind(R.id.textView_item_offer_types)
        TextView textView_item_offer_types;

        @Bind(R.id.textView_item_offer_required_actions)
        TextView textView_item_offer_required_actions;
        @Bind(R.id.textView_payout)
        TextView textView_payout;

        public CommentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClickOffer(null);
        }

        public void setClickListener(ItemClickListenerOffer itemClickListener1) {
            this.clickListener = itemClickListener1;
        }
    }

    public interface ItemClickListenerOffer {

        void onClickOffer(Offers offer);

    }

    public interface DataObserverListener {
        void onDataObserver(int adapterSize);
    }


}


