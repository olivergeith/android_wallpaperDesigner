package de.geithonline.wallpaperdesigner.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class EMailHelper {

	public static Intent createEMailIntent(final String emailTo, final String emailCC, final String emailBCC,
			final String subject, final String emailText, final List<String> filePaths) {
		// need to "send multiple" to get more than one attachment
		final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
		emailIntent.setType("text/plain");

		if (emailTo != null && !emailTo.isEmpty()) {
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { emailTo });
		}
		if (emailCC != null && !emailCC.isEmpty()) {
			emailIntent.putExtra(android.content.Intent.EXTRA_CC, new String[] { emailCC });
		}
		if (emailBCC != null && !emailBCC.isEmpty()) {
			emailIntent.putExtra(android.content.Intent.EXTRA_BCC, new String[] { emailBCC });
		}
		if (subject != null) {
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		} else {
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Empty Subject");
		}
		if (emailText != null) {
			emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
		} else {
			emailIntent.putExtra(Intent.EXTRA_TEXT, "");
		}
		// has to be an ArrayList
		final ArrayList<Uri> uris = new ArrayList<Uri>();
		// convert from paths to Android friendly Parcelable Uri's
		for (final String file : filePaths) {
			final File fileIn = new File(file);
			fileIn.setReadable(true, false);
			final Uri u = Uri.fromFile(fileIn);
			uris.add(u);
		}
		emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
		return emailIntent;
	}

	public static void email(final Context context, final Intent emailIntent) {
		context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}

}
