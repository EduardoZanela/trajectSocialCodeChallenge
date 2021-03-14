  
import './InputCustom.css'

function InputCustom(props) {
  const onKeyDown = e => {
    props.onKeyDown(e.target.value, e.key)
  }

  const onChange = e => {
    props.onChange(e.target.value)
  }

  return (
    <input className="input-custom"  type="text" placeholder={props.placeholder}
      {...(props.onKeyDown ? {onKeyDown: onKeyDown} : {})}
      {...(props.onChange ? {onChange: onChange} : {})}
    />
  )
}

export default InputCustom