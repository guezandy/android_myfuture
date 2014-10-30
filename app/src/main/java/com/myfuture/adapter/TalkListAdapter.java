/**
 * Copyright 2014 Facebook, Inc.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.myfuture.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myfuture.model.Video;
import com.parse.ParseImageView;
import com.myfuture.R;
import com.myfuture.model.Favorites;
import com.myfuture.model.Video;


//TODO: Change to talk list adapter
public class TalkListAdapter extends ArrayAdapter<Video> {

	private boolean isFavoritesView = false;
	private LayoutInflater inflater;

	public TalkListAdapter(Context context, boolean isFavorites) {
		super(context, 0);
		isFavoritesView = isFavorites;
		inflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;

		// If a view hasn't been provided inflate on
		if (view == null) {
			view = inflater.inflate(R.layout.list_item_video, parent, false);
			// Cache view components into the view holder
			holder = new ViewHolder();
			holder.videoLayout = (LinearLayout) view
					.findViewById(R.id.talk_item);
			holder.timeView = (TextView) view.findViewById(R.id.time_view);
			holder.titleView = (TextView) view.findViewById(R.id.title);
			holder.speakerName = (TextView) view
					.findViewById(R.id.speaker_name);
			holder.photo = (ParseImageView) view
					.findViewById(R.id.speaker_photo);
			holder.favoriteButton = (ImageButton) view
					.findViewById(R.id.favorite_button);
			// Tag for lookup later
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		final Video video = getItem(position);

		if (isFavoritesView) {
			LinearLayout videoLayout = holder.videoLayout;

			//int displayColor = video.getCategory().getColor();
			//videoLayout.setBackgroundColor(displayColor);
		}

		TextView timeView = holder.timeView;
        //TODO: Set time of video
		timeView.setText("TIME OF VIDEO");

		TextView titleView = holder.titleView;
		TextView speakerName = holder.speakerName;
		titleView.setText(video.getTitle());


		final ParseImageView photo = holder.photo;
        photo.setParseFile(video.getImage());
	    photo.loadInBackground();


		final ImageButton favoriteButton = holder.favoriteButton;
		if (Favorites.get().contains(video)) {
			if (isFavoritesView) {
				favoriteButton.setImageResource(R.drawable.x);
			} else {
				favoriteButton
						.setImageResource(R.drawable.light_rating_important);
			}
		} else {
			favoriteButton
					.setImageResource(R.drawable.light_rating_not_important);
		}
		favoriteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Favorites favorites = Favorites.get();
				if (favorites.contains(video)) {
					favorites.remove(video);
					favoriteButton
							.setImageResource(R.drawable.light_rating_not_important);
				} else {
					favorites.add(video);
					if (isFavoritesView) {
						favoriteButton.setImageResource(R.drawable.x);
					} else {
						favoriteButton
								.setImageResource(R.drawable.light_rating_important);
					}
				}
				favorites.save(getContext());
			}
		});
		favoriteButton.setFocusable(false);

		if (video.isAlwaysFavorite()) {
			favoriteButton.setVisibility(View.GONE);
			photo.setBackgroundResource(android.R.color.transparent);
		} else {
			favoriteButton.setVisibility(View.VISIBLE);
		}

		return view;
	}

	private static class ViewHolder {
		LinearLayout videoLayout;
		TextView timeView;
		TextView titleView;
		TextView speakerName;
		ParseImageView photo;
		ImageButton favoriteButton;
	}
}
