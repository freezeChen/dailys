package com.frozen.dailys.ui.message


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseFragment
import com.frozen.dailys.component.service.IMService
import com.frozen.dailys.model.FInfo
import com.frozen.dailys.model.Info
import com.frozen.dailys.ui.message.adpter.MessageDetailAdapter
import kotlinx.android.synthetic.main.fragment_message_detail.*


/**
 * Created by csc on 2018/4/26.
 */
class MessageDetailFragment : BaseFragment(), MessageDetailContract.View {

    private lateinit var mPresenter: MessageDetailContract.Presenter

    private val mAdapter by lazy {
        MessageDetailAdapter()
    }

    private var canScroll = true

    companion object {
        fun newInstance(): BaseFragment {
            val args = Bundle()
            val fragment = MessageDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_message_detail, container, false)
        mPresenter = ChatPresenter(this)
        mPresenter.start()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }


        send.setOnClickListener {
            val message = input.text.toString()
            val temp = Info()
            temp.FUserID = fuserid.text.toString()
            temp.FInfo = FInfo(FTOID = "2", FContent = message)

            sendMessage(temp)
        }

        link.setOnClickListener {
            _mActivity.startService(Intent(_mActivity, IMService::class.java))
        }
    }

    override fun newMessage(info: ArrayList<Info>?) {
        info?.let {

            mAdapter.data.addAll(info)



            if (recycler_view.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                mAdapter.notifyDataSetChanged()
                recycler_view.scrollToPosition(mAdapter.data.size - 1)
            }
        }
    }

    override fun sendMessage(info: Info) {
        mPresenter.sendInfo(info)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.stop()
    }
}