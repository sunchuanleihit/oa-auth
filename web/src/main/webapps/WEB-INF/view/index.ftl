<#import "/common/base.ftl" as base/>
<#import "/spring.ftl" as spring/>
<@base.page  "楼口商家进销系统">

        <div class="well">
        可用余额38.2元 = 进货额度（50000) - 已使用额度（49861.8）
        </div>

        <div class="well">
            申请提现中金额：20000元
        </div>

        <div class="well">
            当日订单：22      订单金额：3213.1
        </div>

        <div class="fourBox">
            <div class="pull-left rectBox ">
            <a href="<@spring.url '/order/reviewedList' />">  待处理订单（${orderReviewed}）</a>
              
            </div>

             <div class="pull-right rectBox ">
             <a href="<@spring.url '#' />">   待收货采购单（N）</a>
               
            </div>

            <br/>

             <div class="pull-left rectBox ">
              <a href="<@spring.url '/order/getCBList' />">    待收货预售单（${bookingRecieve}）</a>
            </div>

             <div class="pull-right rectBox ">
                待补货商品（N）
            </div>
        </div>  
</@base.page>